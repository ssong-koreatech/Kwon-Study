package com.microservices.chapter2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

//스프링 애플리케이션 컨텍스트를 유지하는 애플리케이션 클래스
@SpringBootApplication
class Chapter2Application {
	@Bean
	@ConditionalOnExpression("#{'\${service.type}'=='simple'}")
	fun exampleService() : ServiceInterface = ExampleService()
	//ExampleService 클래스의 @Service 어노테이션을 지울 경우 에러가 발생하는데 이를 방지하기 위해서 스프링 애플리케이션 컨텍스트를 유지하는 애플리케이션 클래스에
	//새 인스턴스를 반환하는 함수를 정의하여 빈을 만들어줌
	//타입 : ServiceInterface, 함수 바디 : ExampleService() -> 함수 바디가 생성자임

	@Bean
	@ConditionalOnExpression("#{'\${service.type}'=='advance'}")
	fun advanceService() : ServiceInterface = AdvanceService()
}

@Controller	//Controller 클래스 추가
class FirstController {

	@Autowired
	lateinit var service: ServiceInterface

	@RequestMapping(value = "/user/{name}", method = arrayOf(RequestMethod.GET))	//특정 경로'/user'에 GET 방식으로 매핑
	@ResponseBody
	fun hello(@PathVariable name: String) = service.getHello(name)   //@PathVariable을 사용하면 경로 변수의 값을 파라미터로 받을 수 있음
}

fun main(args: Array<String>) {
	runApplication<Chapter2Application>(*args)	//스프링부트 애플리케이션을 생성하고 실행하며 8080포트를 사용하는 마이크로 서비스 시작
}
