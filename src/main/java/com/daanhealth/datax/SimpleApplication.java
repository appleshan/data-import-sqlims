/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.daanhealth.datax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.daanhealth.datax.datasource.DynamicDataSourceRegister;
import com.daanhealth.datax.service.MainService;

/**
 * Ms Access 数据库 sqlims 导入 东莞 mysql 数据库的服务程序
 * 这里是服务程序启动器。
 * @author appleshan
 * @create 2016年11月18日
 *
 */
@SpringBootApplication
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class SimpleApplication
    implements CommandLineRunner {

	@Autowired
	private MainService mainService;

	public void run(String... args) {
		mainService.syncLisReportByAuto();
		mainService.syncLisReportByHand();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SimpleApplication.class, args);
	}

}
