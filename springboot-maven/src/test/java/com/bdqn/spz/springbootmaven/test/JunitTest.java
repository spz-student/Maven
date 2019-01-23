package com.bdqn.spz.springbootmaven.test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bdqn.spz.springbootmaven.controller.GradeController;
import com.bdqn.spz.springbootmaven.pojo.Grade;
import com.bdqn.spz.springbootmaven.service.IGradeService;

/**
 * @RunWith 在JUnit中有很多个Runner，他们负责调用你的测试代码，
 *          每一个Runner都有各自的特殊功能你要根据需要选择不同的Runner来运行你的测试代码。
 *          如果我们只是简单的做普通Java测试，不涉及spring
 *          Web项目，你可以省略@RunWith注解，这样系统会自动使用默认Runner来运行你的代码。
 */
@RunWith(SpringRunner.class) // 引入Spring-test框架支持,查看源码发现SpringRunner继承SpringJUnit4ClassRunner
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// @WebAppConfiguration
// 由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
public class JunitTest {

	@Resource
	private IGradeService gradeService;

	@Test
	public void grade() {
		List<Grade> grades = gradeService.findGrade();
		for (Grade grade : grades) {
			System.out.println(grade.getGradeName());
		}
	}

	// 读取application.properties文件
	@Value("${com.bdqn.spz.name}")
	private String name;

	@Test
	public void applicationpropertiesTest() {
		System.out.println(name);
	}

	/*
	 * (一)、基础代码以及注解解释 (1) MockServletContext
	 * 由于这是一个新建项目,只有一个helloWord路由,所以我们使用MockServletContext来测试
	 * 使用MockServletContext来构建一个空的WebApplicationContext，这样我们创建的HelloController
	 * 就可以在@Before函数中创建并传递到MockMvcBuilders.standaloneSetup（）函数中。
	 * 
	 * (2)注意 status()content() equalTo()三个方法 import static
	 * org.hamcrest.Matchers.equalTo; import static
	 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.
	 * content; import static
	 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
	 * 
	 * (3)test方法注解介绍
	 * 
	 * @BeforeClass所有测试方法执行前.执行一次，作用:整体初始化
	 * 
	 * @AfterClass所有测试方法完成后,执行一次，作用:销毁和释放资源
	 * 
	 * @Before每个测试方法前执行，作用:初始化方法
	 * 
	 * @After每个测试方法后执行,作用:还原现场
	 * 
	 * @Test(timeout = 1000)测试方法超过1000毫秒,记为超时,测试失败
	 * 
	 * @Test(expected = Exception.class)测试方法期望得到的异常类，如果方法执行没有抛出指定的异常，则测试失败
	 * 
	 * @Ignore(“not ready yet”)执行测试时将忽略掉此方法，如果用于修饰类，则忽略整个类
	 */
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new GradeController()).build();
	}

	@Test
	public void hello() throws Exception {
		 mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		//mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andExpect(content().string(equalTo("hello!!!")));
	}

	/*
	 * (二)、参数化测试 Assert断言方法介绍
	 * 
	 * 1、assertEquals
	 * 
	 * 函数原型：assertEquals([String message],expected,actual)
	 * 
	 * 参数说明： message是个可选的消息，假如提供，将会在发生错误时报告这个消息。 expected是期望值，通常都是用户指定的内容。
	 * actual是被测试的代码返回的实际值。
	 * 
	 * 例：assertEquals("equals","1","1");
	 */
	private String getStr() {
		return "asd";
	}

	@Test
	public void testAssertEquals1() {
		assertEquals("不相等", "a", getStr());
		assertEquals("相等", "asd", getStr());
	}

	/*
	 * 函数原型：assertEquals([String message],expected,actual,tolerance) 参数说明：
	 * message是个可选的消息，假如提供，将会在发生错误时报告这个消息。
	 * 
	 * expected是期望值，通常都是用户指定的内容。
	 * 
	 * actual是被测试的代码返回的实际值。
	 * 
	 * tolerance是误差参数，参加比较的两个浮点数在这个误差之内则会被认为是相等的。
	 */
	@Test
	public void testAssertEquals2() {
		assertEquals("你又不乖了。。。", 3.33, 10 / 3, 0.04);
	}

	/*
	 * 2、assertTrue
	 * 
	 * 函数原型：assertTrue ([String message],Boolean condition)
	 * 
	 * 参数说明：
	 * 
	 * message是个可选的消息，假如提供，将会在发生错误时报告这个消息。
	 * 
	 * condition是待验证的布尔型值。
	 * 
	 * 该断言用来验证给定的布尔型值是否为真，假如结果为假，则验证失败。当然，更有验证为假的测试条件：
	 * 
	 * 函数原型：assertFalse([String message],Boolean condition)
	 * 
	 * 该断言用来验证给定的布尔型值是否为假，假如结果为真，则验证失败。
	 * 
	 * 例： assertTrue("true",1==1); assertFalse("false",2==1);
	 */
	/*
	 * 3、assertNull
	 * 
	 * 函数原型：assertNull([String message],Object object)
	 * 
	 * 参数说明：
	 * 
	 * message是个可选的消息，假如提供，将会在发生错误时报告这个消息。
	 * 
	 * object是待验证的对象。
	 * 
	 * 该断言用来验证给定的对象是否为null，假如不为null，则验证失败。相应地，还存在能够验证非null的断言：
	 * 
	 * 函数原型：assertNotNull([String message],Object object)
	 * 
	 * 该断言用来验证给定的对象是否为非null，假如为null，则验证失败。
	 * 
	 * 例：assertNull("null",null);
	 * 
	 * assertNotNull("not null",new String());
	 */
	@Test
	public void testAssertNull() {
		// assertNull("null",null);

		// List list = new ArrayList();
		// assertNull("new ArrayList is not null" ,list);

		List<?> list1 = Collections.emptyList();
		assertNull("emptyList is not null", list1);
	}

	/*
	 * 4、assertSame
	 * 
	 * 函数原型：assertSame ([String message], expected,actual)
	 * 
	 * 参数说明：
	 * 
	 * message是个可选的消息，假如提供，将会在发生错误时报告这个消息。
	 * 
	 * expected是期望值。
	 * 
	 * actual是被测试的代码返回的实际值。
	 * 
	 * 该断言用来验证expected参数和actual参数所引用的是否是同一个对象，假如不是，则验证失败。
	 * 
	 * 函数原型：assertNotSame ([String message], expected,actual)
	 * 
	 * 该断言用来验证expected参数和actual参数所引用的是否是不同对象，假如所引用的对象相同，则验证失败。
	 * 
	 * 例：assertSame("same",2,4-2); assertNotSame("not same",2,4-3);
	 */
	@Test
	public void testSame() {
		// assertSame("same:这俩对象真不一样！！！",2,4-2);
		// assertSame("same:这俩对象真不一样！！！",2L,4-2);
		// assertSame("same:这俩对象真不一样！！！",new String("aaa"),new String("aaa"));
		// assertSame("same:这俩对象一样！！！","aaa","aaa");
		// assertSame("same:这俩对象真不一样！！！","aaa",new String("aaa"));

		String a, b;
		a = "aaaaa";
		b = a;
		assertSame("same:这俩对象一样！！！", a, b);
		assertSame("same:这俩对象一样！！！", a, "aaaaa");
	}

	/*
	 * 5、fail
	 * 
	 * 函数原型：fail([String message])
	 * 
	 * 参数说明：
	 * 
	 * message是个可选的消息，假如提供，将会在发生错误时报告这个消息。
	 * 
	 * 该断言会使测试立即失败，通常用在测试不能达到的分支上（如异常）。 测试方法是否进行完毕，如果没有则报错并中断，如下：i 的值不同，结果不同。
	 */
	@Test
	public void testFail() {
		for (int i = 0; i < 10; i++) {
			if (i == 5) {
				fail("fail....");
			}
			System.out.println(i);
		}
	}

	/*
	 * 6、assertArrayEquals 函数原型：assertArrayEquals([String message], Object[]
	 * expecteds,Object[] actuals) 参数说明：
	 * message是个可选的消息，假如提供，将会在发生错误时报告这个消息,以及不一致的具体信息（数组长度、首次出现不一致的地方..）
	 * 
	 * expecteds 是多个期望值。
	 * 
	 * actuals是被测试的代码返回的实际值。
	 */
	@Test
	public void testAssertArrayEquals() {
		// assertArrayEquals("这俩数组不一样？？",new Integer[]{1,3,5,7,9},new
		// Integer[]{1,3,5});
		/*
		 * java.lang.AssertionError: 这俩数组不一样？？: array lengths differed,
		 * expected.length=5 actual.length=3
		 */

		// assertArrayEquals("这俩数组不一样？？",new Integer[]{1,3,5,7,9},new
		// Integer[]{1,3,5,7,9});

		// assertArrayEquals("这俩数组不一样？？",new Integer[]{1,3,5,7,9},new
		// Integer[]{2,4,6,8,10});
		/* 这俩数组不一样？？: arrays first differed at element [0]; .... */

		assertArrayEquals("这俩数组不一样？？", new Integer[] { 1, 3, 5, 7, 9 }, new Integer[] { 1, 3, 6, 8, 10 });
		/*
		 * 这俩数组不一样？？: arrays first differed at element [2]; Expected :5 Actual
		 * :6
		 */
	}
}
