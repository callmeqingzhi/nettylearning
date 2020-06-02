package com.cz.nio;

import java.nio.IntBuffer;

public class BaseBuffer {

    public static void main(String[] args) {
        // 创建一个容量为5的buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);

        // 存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }
        // 读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()){

            System.out.println(intBuffer.get());
        }


    }
}
