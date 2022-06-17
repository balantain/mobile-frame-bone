package driver;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;
import spring.annotations.Block;
import spring.annotations.PageObject;

import java.lang.reflect.*;
import java.util.List;

public class CustomFieldDecorator implements FieldDecorator{
    protected ElementLocatorFactory factory;

    public CustomFieldDecorator(ElementLocatorFactory factory){
        this.factory = factory;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field){
        if(field.isAnnotationPresent(PageObject.class) || field.isAnnotationPresent(Block.class)){
            return proxyInside(field);
        }

        if(!(WebElement.class.isAssignableFrom(field.getType()) || isDecoratableList(field))){
            return null;
        }

        ElementLocator locator = factory.createLocator(field);
        if(locator == null){
            return null;
        }

        if(WebElement.class.isAssignableFrom(field.getType())){
            return proxyForLocator(loader, locator);
        }else if(List.class.isAssignableFrom(field.getType())){
            return proxyForListLocator(loader, locator);
        }else{
            return null;
        }
    }

    protected boolean isDecoratableList(Field field){
        if(!List.class.isAssignableFrom(field.getType())){
            return false;
        }

        // Type erasure in Java isn't complete. Attempt to discover the generic
        // type of the list.
        Type genericType = field.getGenericType();
        if(!(genericType instanceof ParameterizedType)){
            return false;
        }

        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

        if(!WebElement.class.equals(listType)){
            return false;
        }

        return field.getAnnotation(FindBy.class) != null || field.getAnnotation(FindBys.class) != null || field.getAnnotation(FindAll.class) != null;
    }

    protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator){
        InvocationHandler handler = new LocatingElementHandler(locator);

        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }

    @SuppressWarnings("unchecked")
    protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator){
        InvocationHandler handler = new LocatingElementListHandler(locator);

        List<WebElement> proxy;
        proxy = (List<WebElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
        return proxy;
    }

    protected Object proxyInside(Field field){
        var obj = CustomFieldDecorator.instantiatePage(SingletonDriver.getDriver(), field.getClass());
        PageFactory.initElements(new CustomFieldDecorator(new DefaultElementLocatorFactory(SingletonDriver.getDriver())), obj);
        return obj;
    }

    private static <T> T instantiatePage(SearchContext searchContext, Class<T> pageClassToProxy){
        try{
            try{
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(searchContext);
            }catch(NoSuchMethodException e){
                return pageClassToProxy.getDeclaredConstructor().newInstance();
            }
        }catch(ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }
}
