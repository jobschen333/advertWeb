package com.web.learning.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆异常
 * @author chxy
 */
public class HeapOOM {

    static class OOMObject {

    }

    //java.lang.OutOfMemoryError: Java heap space 定义实例对象的时候 堆内存报错了
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
