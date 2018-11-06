package com.something.xml;

import java.util.ArrayList;

public class JaxbWrapperSupport {
    /**
    private List<JaxbObjectWrapperFactory> wrapperFactories = new ArrayList();

    public JaxbWrapperSupport() {
    }

    public Object wrap(Object o) {
        Optional<JaxbObjectWrapperFactory> findFactory = this.wrapperFactories.stream().filter((f) -> {
            return f.supports(o.getClass());
        }).findFirst();
        return findFactory.map((f) -> {
            return f.createWrapper(o);
        }).orElse(o);
    }

    public Object unwrap(Object o) {
        return o instanceof JaxbObjectWrapper ? ((JaxbObjectWrapper)o).getObject() : o;
    }

    public boolean supports(Class clazz) {
        return JaxbObjectWrapper.class.isAssignableFrom(clazz) || this.wrapperFactories.stream().filter((f) -> {
            return f.supports(clazz);
        }).findAny().isPresent();
    }

    public Class getWrapperClass(Class clazz) {
        Optional<JaxbObjectWrapperFactory> findFactory = this.wrapperFactories.stream().filter((f) -> {
            return f.supports(clazz);
        }).findFirst();
        return (Class)findFactory.map((f) -> {
            return f.getWrapperClass();
        }).orElse(clazz);
    }

    public void setWrapperFactories(List<JaxbObjectWrapperFactory> wrapperFactories) {
        this.wrapperFactories = wrapperFactories;
    }
     */
}