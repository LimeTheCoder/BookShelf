package com.limethecoder.controller.editor;

import com.limethecoder.data.service.Service;

import java.beans.PropertyEditorSupport;

public abstract class AbstractEditor<T> extends PropertyEditorSupport {
    protected abstract Service<T> getService();

    @Override
    public void setAsText(String text) {
        T obj = getService().findOne(Integer.valueOf(text));
        setValue(obj);
    }
}
