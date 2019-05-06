package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service

//@Service    //해당 클래스가 Service라고 알리기 위해 달아준 어노테이션
class ExampleService : ServiceInterface {

    @Value(value = "\${service.message.text}")
    private lateinit var text: String

    override fun getHello(name : String) = "$text $name" //getHello 메소드에서 결과값 출력
}