package com.moses.boot.sample.messageConverter;

import com.moses.boot.sample.model.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * 自定义的MessageConverter, 将User对象与Properties文本互转, 就像Json与User互转
 * @author Moses
 *
 */
public class PropertiesUserHttpMessageConverter extends AbstractHttpMessageConverter<User> {

    public PropertiesUserHttpMessageConverter() {
        super(MediaType.valueOf("application/properties+user"));		//自定义类型
        setDefaultCharset(Charset.forName("UTF-8"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(User.class);
    }

    /**
     * 将properties内容转成Person对象
     */
    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        /**
         * user.id = 1
         * user.name = jmc
         */
        InputStream inputStream = inputMessage.getBody();
        Properties props = new Properties();
        props.load(new InputStreamReader(inputStream, getDefaultCharset()));
        User p = new User();
        p.setId(Integer.valueOf(props.getProperty("user.id")));
        p.setName(props.getProperty("user.name"));
        return p;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        Properties props = new Properties();
        props.setProperty("user.id", String.valueOf(user.getId()));
        props.setProperty("user.name", user.getName());

        props.store(new OutputStreamWriter(outputStream, getDefaultCharset()), "");
    }

}
