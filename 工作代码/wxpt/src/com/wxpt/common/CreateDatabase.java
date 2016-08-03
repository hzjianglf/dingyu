package com.wxpt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {

	// 创建数据库
	@SuppressWarnings("static-access")
	public static boolean createDateBase(String DateName) {
		boolean f = false;
		JDBC_test DB = new JDBC_test();
		String url = null;
		try {
			/*
			 * Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * //一开始必须填一个已经存在的数据库 String url =
			 * "jdbc:mysql://211.154.224.228:3306/webchat?useUnicode=true&amp;characterEncoding=utf-8"
			 * ; Connection conn = DriverManager.getConnection(url, "huida",
			 * "huida");
			 */
			// conn=DriverManager.getConnection(url, user, pwd);
			Connection conn = DB.getConnection2();
			Statement stat = conn.createStatement();

			// 创建数据库
			stat.executeUpdate("create database if not exists " + DateName + "");

			// 打开创建的数据库
			stat.close();
			conn.close();

			url = "jdbc:mysql://localhost:3306/" + DateName
					+ "?useUnicode=true&characterEncoding=utf-8";
			// url = "jdbc:mysql://211.154.224.231:3306/" + DateName +
			// "?useUnicode=true&characterEncoding=utf-8";
			conn = DriverManager.getConnection(url, "wxpt", "wxpt");
			stat = conn.createStatement();

			// 1创建表user
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `user` ("
					+ " `userId` int(11) NOT NULL AUTO_INCREMENT,"
					+ "  `userNum` varchar(100) NOT NULL,"
					+ " `userName` varchar(100) NOT NULL,"
					+ " PRIMARY KEY (`userId`)" + ")"
					+ " ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 2创建userCount
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `userCount` ("
					+ " `countId` int(11) NOT NULL AUTO_INCREMENT,"
					+ " `userId` int(11) NOT NULL,"
					+ " `sendUser` varchar(100) NOT NULL,"
					+ " `sendTime` varchar(100) NOT NULL,"
					+ " PRIMARY KEY (`countId`)," + " KEY `userId` (`userId`)"
					+ ")"
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 条件约束
			stat.executeUpdate("ALTER TABLE `userCount`"
					+ " ADD CONSTRAINT `userCount_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 3创建question_type
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `question_type` ("
					+ "`typeId` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`typeName` varchar(100) NOT NULL,"
					+ "`typeNum` int(11) NOT NULL," + "PRIMARY KEY (`typeId`)"
					+ ")"
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			stat.executeUpdate("INSERT INTO `question_type`(`typeId`, `typeName`, `typeNum`) VALUES (-1,'null',-1)");
			stat.executeUpdate("INSERT INTO `question_type`(`typeId`, `typeName`, `typeNum`) VALUES (1,'简单',0)");
			stat.executeUpdate("INSERT INTO `question_type`(`typeId`, `typeName`, `typeNum`) VALUES (2,'中等',1)");
			stat.executeUpdate("INSERT INTO `question_type`(`typeId`, `typeName`, `typeNum`) VALUES (3,'困难',2)");
			// 4创建question
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `question` ("
					+ "`questionid` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`qustion_content` varchar(100) NOT NULL,"
					+ "`answer_a` varchar(100) DEFAULT NULL,"
					+ "`answer_b` varchar(100) DEFAULT NULL,"
					+ "`answer_c` varchar(100) DEFAULT NULL,"
					+ "`answer_d` varchar(100) DEFAULT NULL,"
					+ "`right_answer` varchar(100) DEFAULT NULL,"
					+ "`question_title` varchar(100) DEFAULT NULL,"
					+ "`type` int(11) NOT NULL DEFAULT '0',"
					+ "`typeId` int(11) NOT NULL,"
					+ "PRIMARY KEY (`questionid`)," + "KEY `typeId` (`typeId`)"
					+ ") "
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 约束
			stat.executeUpdate("ALTER TABLE `question`"
					+ "ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `question_type` (`typeId`) ON DELETE CASCADE ON UPDATE CASCADE;");
			stat.executeUpdate("INSERT INTO `question`(`questionid`, `qustion_content`, `answer_a`, `answer_b`, `answer_c`, `answer_d`, `right_answer`, `question_title`, `type`, `typeId`) VALUES (-1,'',null,null ,null ,null,null,null,-1,-1)");
			// 5answer
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `answer` ("
					+ "`answerid` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`sendUser` varchar(200) NOT NULL,"
					+ "`answertime` varchar(100) DEFAULT NULL,"
					+ "`questionid` int(11) NOT NULL,"
					+ "`send_answer` varchar(100) DEFAULT NULL,"
					+ "`state` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`answerid`),"
					+ "KEY `questionid` (`questionid`)" + ")"
					+ " ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 约束
			stat.executeUpdate("ALTER TABLE `answer`"
					+ "ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`questionid`) REFERENCES `question` (`questionid`) ON DELETE CASCADE ON UPDATE CASCADE;");
			stat.executeUpdate("INSERT INTO `answer` (`answerid`, `sendUser`, `answertime`, `questionid`, `send_answer`, `state`) VALUES(1, '', NULL, -1, NULL, NULL);");

			// 6创建keywords
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `keywords` ("
					+ "`keywordID` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`keywordcontent` varchar(100) NOT NULL,"
					+ "`wordCount` varchar(100) DEFAULT NULL,"
					+ "`fileCount` varchar(100) DEFAULT NULL,"
					+ "`imagesCount` varchar(100) DEFAULT NULL,"
					+ "`rule` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`keywordID`)" + ") "
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 7创建keywordexplicit
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `keywordexplicit` ("
					+ "`explicitID` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`keywordID` int(11) NOT NULL,"
					+ "`eContent` varchar(400) NOT NULL,"
					+ "`ekey` varchar(100) NOT NULL,"
					+ "`addTime` varchar(200) NOT NULL,"
					+ "`einstruction` varchar(400) DEFAULT NULL,"
					+ "`title` varchar(400) DEFAULT NULL,"
					+ "`url` varchar(400) DEFAULT NULL,"
					+ "`type` int(11) NOT NULL DEFAULT '0',"
					+ "PRIMARY KEY (`explicitID`),"
					+ "KEY `keywordID` (`keywordID`)" + ")"
					+ " ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 约束
			stat.executeUpdate(" ALTER TABLE `keywordexplicit`"
					+ "ADD CONSTRAINT `keywordexplicit_ibfk_1` FOREIGN KEY (`keywordID`) REFERENCES `keywords` (`keywordID`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 8创建answer_records
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `answer_records` ("
					+ "`recordId` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`answer_user` varchar(100) NOT NULL,"
					+ "`answer_time` varchar(100) NOT NULL,"
					+ "`type` int(11) NOT NULL DEFAULT '0',"
					+ "PRIMARY KEY (`recordId`)" + ")"
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			stat.executeUpdate("INSERT INTO `answer_records`( `answer_user`, `answer_time`, `type`) VALUES ('','',-1)");
			// 9创建表luckanwer
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `luckanwer` ("
					+ "`luckanswerid` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`recordId` int(11) NOT NULL,"
					+ "`add_time` varchar(100) NOT NULL,"
					+ "`state` int(11) NOT NULL DEFAULT '0',"
					+ "`update_time` varchar(100) DEFAULT NULL,"
					+ "PRIMARY KEY (`luckanswerid`),"
					+ "KEY `recordId` (`recordId`)" + ")"
					+ " ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 10创建luckUser
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `luckUser` ("
					+ "`luckId` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`send_user` varchar(100) NOT NULL,"
					+ "`add_time` varchar(100) NOT NULL,"
					+ "`state` int(11) NOT NULL DEFAULT '0',"
					+ "`send_time` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`luckId`)" + ")"
					+ " ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 约束
			stat.executeUpdate("ALTER TABLE `luckanwer`"
					+ "ADD CONSTRAINT `luckanwer_ibfk_1` FOREIGN KEY (`recordId`) REFERENCES `answer_records` (`recordId`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 11创建checkIn
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `checkIn` ("
					+ "`checkId` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`checkuser` varchar(200) DEFAULT NULL,"
					+ "`checTime` varchar(100) DEFAULT NULL,"
					+ "`checkNum` varchar(100) DEFAULT NULL,"
					+ "`score` varchar(100) DEFAULT NULL,"
					+ "`count` int(11) DEFAULT NULL,"
					+ " PRIMARY KEY (`checkId`)" + ")"
					+ " ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 12创建imageroll
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `imageroll` ("
					+ "`image_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`upload_image` varchar(100) DEFAULT NULL,"
					+ "`upload_time` varchar(100) DEFAULT NULL,"
					+ "`template_count` int(11) DEFAULT NULL,"
					+ " PRIMARY KEY (`image_id`)" + ")"
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 13创建prize
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `prize` ("
					+ "`prizeId` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`prizegrade` int(11) DEFAULT NULL,"
					+ "`prizenum` varchar(100) DEFAULT NULL,"
					+ "`probability` varchar(100) DEFAULT NULL,"
					+ "`prizeuser` varchar(200) DEFAULT NULL,"
					+ "`prizeTime` varchar(1000) DEFAULT NULL,"
					+ "`state` int(11) DEFAULT NULL,"
					+ " `isstate` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`prizeId`)" + ")"
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 14创建 member
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `member` ("
					+ "`member_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ " `card_id` varchar(100) DEFAULT NULL,"
					+ "`password` varchar(100) DEFAULT NULL,"
					+ "`weixin_id` varchar(100) DEFAULT NULL,"
					+ "`business_id` varchar(100) DEFAULT NULL,"
					+ "`username` varchar(100) DEFAULT NULL,"
					+ "`sex` int(11) DEFAULT '0',"
					+ "`age` int(11) DEFAULT '0',"
					+ "`add_time` date DEFAULT NULL,"
					+ "`end_time` date DEFAULT NULL,"
					+ "`address` varchar(100) DEFAULT NULL,"
					+ "`phone` varchar(100) DEFAULT NULL,"
					+ "`member_points` int(11) DEFAULT '0',"
					+ "`save_money` varchar(100) DEFAULT NULL,"
					+ "`member_grade` int(11) DEFAULT '0',"
					+ "`member_freeze` int(11) DEFAULT '0',"
					+ "PRIMARY KEY (`member_id`)" + ") "
					+ "ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 15 创建message
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `message` ("
					+ "`message_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`member_id` int(11) DEFAULT NULL,"
					+ "`message_title` varchar(100)  DEFAULT NULL,"
					+ "`message_content` varchar(100)  DEFAULT NULL,"
					+ "`message_type` int(11) DEFAULT NULL,"
					+ "`time` varchar(100) DEFAULT NULL,"
					+ "`message_parentid` int(11) DEFAULT '0',"
					+ "PRIMARY KEY (`message_id`),"
					+ "KEY `member_id` (`member_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制
			stat.executeUpdate("ALTER TABLE `message`"
					+ "ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 16 创建表storerecord
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `storerecord` ("
					+ "`id` int(100) NOT NULL AUTO_INCREMENT,"
					+ "`money` double NOT NULL,"
					+ "`recordtime` varchar(100) NOT NULL,"
					+ "`member_id` int(100) NOT NULL,"
					+ "`businessName` varchar(100)  NOT NULL,"
					+ "`present_money` double NOT NULL,"
					+ "PRIMARY KEY (`id`),"
					+ "KEY `member_id` (`member_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制
			stat.executeUpdate("ALTER TABLE `storerecord`"
					+ "ADD CONSTRAINT `storerecord_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 17创建 integrals
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `integrals` ("
					+ "`integrals_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`member_id` int(11) NOT NULL,"
					+ "`integrals_one` int(100) DEFAULT '0',"
					+ "`integrals_time` date DEFAULT NULL,"
					+ "`integrals_business` varchar(100)  DEFAULT NULL,"
					+ "`integrals_remark` varchar(100)  DEFAULT NULL,"
					+ "PRIMARY KEY (`integrals_id`),"
					+ "KEY `member_id` (`member_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制
			stat.executeUpdate("ALTER TABLE `integrals`"
					+ "ADD CONSTRAINT `integrals_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 18 创建 card
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `card` ("
					+ "`card_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`card_name` varchar(200) DEFAULT NULL,"
					+ "`card_image` varchar(200) DEFAULT NULL,"
					+ "`card_count` int(11) DEFAULT NULL,"
					+ "`card_type` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`card_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 19 创建card_records
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `card_records` ("
					+ " `card_records_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ " `card_id` int(11) DEFAULT NULL,"
					+ "`card_get_time` varchar(200) DEFAULT NULL,"
					+ "`exchange_awd_time` varchar(200) DEFAULT NULL,"
					+ "`card_userName` varchar(200) DEFAULT NULL,"
					+ "`card-prize-desc` varchar(300) DEFAULT NULL,"
					+ "PRIMARY KEY (`card_records_id`),"
					+ "KEY `card_id` (`card_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 20 创建move
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `move` ("
					+ "`move_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`move_state_desc` varchar(200) DEFAULT NULL,"
					+ "`move_name` varchar(200) DEFAULT NULL,"
					+ "`move_content` varchar(500) DEFAULT NULL,"
					+ "`move_start_time` varchar(200) DEFAULT NULL,"
					+ "`move_end_time` varchar(200) DEFAULT NULL,"
					+ "`move_state` int(11) DEFAULT NULL,"
					+ "`awardTime` varchar(200) DEFAULT NULL,"
					+ "`gailv` varchar(500) DEFAULT '0;0;0;0;0;0;0;0;0;0',"
					+ "`probability` varchar(200) DEFAULT '0;0;0;0;0;0;0;0;0;0',"
					+ "PRIMARY KEY (`move_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 21 创建vote
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `vote` ("
					+ "`voteId` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`voteStartTime` varchar(100) DEFAULT NULL,"
					+ "`voteEndTime` varchar(100) DEFAULT NULL,"
					+ "`state` int(11) DEFAULT NULL,"
					+ "`voteUser` varchar(100) DEFAULT NULL,"
					+ "PRIMARY KEY (`voteId`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 22 创建 activity
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `activity` ("
					+ "`activity_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`activity_title` varchar(100)  DEFAULT NULL,"
					+ "`activity_content` varchar(100)  DEFAULT NULL,"
					+ "`activity_starttime` varchar(100)  DEFAULT NULL,"
					+ "`activity_endtime` varchar(100)  DEFAULT NULL,"
					+ "`image_url` varchar(100) DEFAULT NULL,"
					+ "PRIMARY KEY (`activity_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 限制表
			stat.executeUpdate("ALTER TABLE `card_records`"
					+ "ADD CONSTRAINT `card_records_ibfk_1` FOREIGN KEY (`card_id`) REFERENCES `card` (`card_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 23 创建reply
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `reply` ("
					+ "`reply_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`reply_type` int(11) NOT NULL DEFAULT '0',"
					+ "`keywordID` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`reply_id`),"
					+ "KEY `keywordID` (`keywordID`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 线指表
			stat.executeUpdate(" ALTER TABLE `reply`"
					+ "ADD CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`keywordID`) REFERENCES `keywords` (`keywordID`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 插入数据
			stat.executeUpdate("INSERT INTO `reply` (`reply_id`, `reply_type`, `keywordID`) VALUES"
					+ "(1, 0, null)," + "(2, 1, null);");
			// 24创建coupons
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `coupons` ("
					+ "`coupons_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`member_id` int(11) DEFAULT NULL,"
					+ "`activity_id` int(11) DEFAULT NULL,"
					+ "`coupons_title` varchar(200)  DEFAULT NULL,"
					+ "`coupons_content` varchar(300) DEFAULT NULL,"
					+ "`coupons_remark` varchar(200)  DEFAULT NULL,"
					+ "`coupons_standby` varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`coupons_id`),"
					+ "KEY `member_id` (`member_id`),"
					+ "KEY `activity_id` (`activity_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;");
			// 限制表 `coupons`
			stat.executeUpdate("ALTER TABLE `coupons`"
					+ "ADD CONSTRAINT `coupons_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "ADD CONSTRAINT `coupons_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 25 创建表 area
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `area` ("
					+ "`area_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`area_name` varchar(100) NOT NULL,"
					+ "`add_time` varchar(100) NOT NULL,"
					+ "`update_time` varchar(100) NOT NULL,"
					+ "`user_id` int(11) NOT NULL,"
					+ "`update_user` int(11) NOT NULL,"
					+ "`location_x` varchar(100) DEFAULT NULL,"
					+ "`location_y` varchar(100) DEFAULT NULL,"
					+ "`area_no` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`area_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 26 创建表 canton
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `canton` ("
					+ "`canton_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`canton_name` varchar(100) NOT NULL,"
					+ "`add_time` varchar(100) NOT NULL,"
					+ "`update_time` varchar(100) NOT NULL,"
					+ "`user_id` int(11) NOT NULL,"
					+ "`update_user` int(11) DEFAULT NULL,"
					+ "`canton_no` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`canton_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 27 创建表 industry
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `industry` ("
					+ "`industry_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`industry_name` varchar(100) NOT NULL,"
					+ "`add_time` varchar(100) NOT NULL,"
					+ "`update_time` varchar(100) NOT NULL,"
					+ "`user_id` int(11) NOT NULL,"
					+ "`update_user` int(11) DEFAULT NULL,"
					+ "`industry_no` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`industry_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制表
			// 28 创建 customers
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `customers` ("
					+ "`customers_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`customers_name` varchar(200) NOT NULL,"
					+ "`industry_id` int(11) NOT NULL,"
					+ "`area_id` int(11) NOT NULL,"
					+ "`canton_id` int(11) DEFAULT NULL,"
					+ "`customers_address` varchar(1000) DEFAULT NULL,"
					+ "`model` varchar(1000) DEFAULT NULL,"
					+ "`count` varchar(200) DEFAULT '0',"
					+ "`introduce` varchar(1000) DEFAULT NULL,"
					+ "`location_x` varchar(200) DEFAULT NULL,"
					+ "`location_y` varchar(200) DEFAULT NULL,"
					+ "`add_time` varchar(200) DEFAULT NULL,"
					+ "`state` int(11) NOT NULL DEFAULT '0',"
					+ "`from_username` varchar(200) DEFAULT NULL,"
					+ "`customers_no` varchar(100) NOT NULL,"
					+ "`customers_image` varchar(100) DEFAULT NULL,"
					+ "PRIMARY KEY (`customers_id`),"
					+ "KEY `industry_id` (`industry_id`),"
					+ "KEY `area_id` (`area_id`),"
					+ "KEY `canton_id` (`canton_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制表 customers
			stat.executeUpdate("ALTER TABLE `customers`"
					+ "ADD CONSTRAINT `customers_ibfk_3` FOREIGN KEY (`canton_id`) REFERENCES `canton` (`canton_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`industry_id`) REFERENCES `industry` (`industry_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "ADD CONSTRAINT `customers_ibfk_2` FOREIGN KEY (`area_id`) REFERENCES `area` (`area_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 29 创建表 fans
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `fans` ("
					+ "`fans_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`fans_name` varchar(100) NOT NULL,"
					+ "`fans_value` varchar(100) NOT NULL,"
					+ "PRIMARY KEY (`fans_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 30 创建表 privilege
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `privilege` ("
					+ "`limit_add_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`limit_id` int(11) NOT NULL,"
					+ "`limit_name` varchar(200) NOT NULL,"
					+ "`limit_statement` varchar(1024) NOT NULL,"
					+ "`limit_parent_id` int(11) DEFAULT NULL,"
					+ "`limits_type` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`limit_add_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 31 创建 radius
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `radius` ("
					+ "`radius_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`radius_content` int(11) NOT NULL DEFAULT '5',"
					+ "`center_name` varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`radius_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 插入数据
			stat.executeUpdate("INSERT INTO `radius` (`radius_content`, `center_name`) VALUES"
					+ "(100, '济南');");
			// 32 创建 role
			stat.executeUpdate(" CREATE TABLE IF NOT EXISTS `role` ("
					+ "`role_add_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`role_id` varchar(200) NOT NULL,"
					+ "`role_name` varchar(200) NOT NULL,"
					+ "`role_parent_id` int(11) DEFAULT NULL,"
					+ "`role_statement` varchar(1024) NOT NULL,"
					+ "`limit_list` varchar(1024) NOT NULL,"
					+ "`role_type` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`role_add_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 33 创建 user_site_menu
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `user_site_menu` ("
					+ "`menu_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`menu_name` varchar(255) DEFAULT NULL,"
					+ "`menu_parent` int(11) DEFAULT NULL,"
					+ "`menu_group` int(11) DEFAULT NULL,"
					+ "`image_value` varchar(100) DEFAULT NULL,"
					+ "PRIMARY KEY (`menu_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 34 创建 user_site_option
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `user_site_option` ("
					+ "`option_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`option_name` varchar(255) DEFAULT NULL,"
					+ "`option_value` varchar(255) DEFAULT NULL,"
					+ "`url` varchar(100) DEFAULT NULL,"
					+ "PRIMARY KEY (`option_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;");
			// 35 创建 user_site_page
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `user_site_page` ("
					+ "`page_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`page_title` varchar(255) DEFAULT NULL,"
					+ "`page_menu` int(11) NOT NULL,"
					+ "`page_group` int(11) DEFAULT NULL,"
					+ "`page_time` datetime DEFAULT NULL,"
					+ "`page_url` varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`page_id`),"
					+ "KEY `page_menu` (`page_menu`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制
			stat.executeUpdate("ALTER TABLE `user_site_page`"
					+ "ADD CONSTRAINT `user_site_page_ibfk_1` FOREIGN KEY (`page_menu`) REFERENCES `user_site_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 创建 36 user_site_pagemeta
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `user_site_pagemeta` ("
					+ "`meta_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`meta_page` int(11) NOT NULL,"
					+ "`meta_key` varchar(255) DEFAULT NULL,"
					+ "`meta_value` longtext,"
					+ "PRIMARY KEY (`meta_id`),"
					+ "KEY `meta_page` (`meta_page`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制表
			stat.executeUpdate("ALTER TABLE `user_site_pagemeta`"
					+ "ADD CONSTRAINT `user_site_pagemeta_ibfk_1` FOREIGN KEY (`meta_page`) REFERENCES `user_site_page` (`page_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 37 menu
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `menu` ("
					+ "`menu_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`menu_name` varchar(200) DEFAULT NULL,"
					+ "`menu_parent_id` int(11) NOT NULL,"
					+ "`menu_type` varchar(200) DEFAULT NULL,"
					+ "`menu_url` varchar(500) DEFAULT NULL,"
					+ "`menu_key` varchar(500) DEFAULT NULL,"
					+ "PRIMARY KEY (`menu_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 38 product_type
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `product_type` ("
					+ "`product_type_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`parent_id` int(11) NOT NULL,"
					+ "`type_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,"
					+ "PRIMARY KEY (`product_type_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 39 product
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `product` ("
					+ "`product_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`product_num` varchar(100) DEFAULT NULL,"
					+ "`product_name` varchar(200) NOT NULL,"
					+ "`product_brand` varchar(100) DEFAULT NULL,"
					+ "`inventory_num` int(11) NOT NULL DEFAULT '0',"
					+ "`price` double NOT NULL,"
					+ " `cheap_price` double DEFAULT '0',"
					+ "`sell_num` int(11) NOT NULL DEFAULT '0',"
					+ "`product_details` longtext NOT NULL,"
					+ "`product_overview` text,"
					+ "`product_color` varchar(200) DEFAULT NULL,"
					+ "`product_ximage` varchar(200) NOT NULL,"
					+ " `product_dimage` varchar(200) NOT NULL,"
					+ "`product_addtime` varchar(200) NOT NULL,"
					+ "`product_type_id` int(11) NOT NULL,"
					+ "`product_recomme` varchar(200) NOT NULL DEFAULT '未推荐',"
					+ "`product_update_time` varchar(100) DEFAULT NULL,"
					+ " PRIMARY KEY (`product_id`),"
					+ " KEY `product_type_id` (`product_type_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 40 appraise
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `appraise` ("
					+ "`appraise_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`product_id` int(11) NOT NULL,"
					+ "`member_id` int(11) NOT NULL,"
					+ "`appraise_content` varchar(1024) NOT NULL,"
					+ "`appraise_time` varchar(200) NOT NULL,"
					+ "`appraise_add` varchar(1024) NOT NULL,"
					+ "`appraise_add_time` varchar(200) NOT NULL,"
					+ "`appraise_huifu` varchar(1024) DEFAULT NULL,"
					+ "`appraise_huifu_time` varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`appraise_id`),"
					+ "KEY `product_id` (`product_id`),"
					+ "KEY `member_id` (`member_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 41 logistics
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `logistics` ("
					+ "`logistics_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`logistics_way` varchar(200) NOT NULL,"
					+ "`logistics_price` double NOT NULL,"
					+ "PRIMARY KEY (`logistics_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 42 order
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `order` ("
					+ "`order_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`order_product_id` varchar(500) NOT NULL,"
					+ "`order_num` varchar(200) NOT NULL,"
					+ "`order_time` varchar(200) NOT NULL,"
					+ "`pay_money` double NOT NULL,"
					+ "`pay_time` varchar(200) DEFAULT NULL,"
					+ "`pay_type` int(11) NOT NULL DEFAULT '0',"
					+ "`receive_person` varchar(200) DEFAULT NULL,"
					+ "`receive_address` varchar(500) DEFAULT NULL,"
					+ "`receive_phone` varchar(100) DEFAULT NULL,"
					+ "`send_type` int(11) NOT NULL DEFAULT '0',"
					+ "`Logistics_id` int(11) DEFAULT NULL,"
					+ "`member_id` int(11) NOT NULL,"
					+ "`order_leave` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,"
					+ "`order_type` int(11) DEFAULT '0',"
					+ "`order_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,"
					+ "`take_type` int(11) DEFAULT '0',"
					+ "`send_time` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,"
					+ "PRIMARY KEY (`order_id`),"
					+ "KEY `member_id` (`member_id`),"
					+ "KEY `Logistics_id` (`Logistics_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 43 buy_product
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `buy_product` ("
					+ "`buy_product_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`buy_product_order` int(11) NOT NULL,"
					+ "`buy_product_product` int(11) NOT NULL,"
					+ "`buy_product_sum` int(11) NOT NULL,"
					+ "PRIMARY KEY (`buy_product_id`),"
					+ "KEY `buy_product_order` (`buy_product_order`),"
					+ "KEY `buy_product_product` (`buy_product_product`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 44 shopping_address
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `shopping_address` ("
					+ "`shopping_address_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`member_id` int(11) NOT NULL,"
					+ "`name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,"
					+ "`address` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,"
					+ "`phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,"
					+ "PRIMARY KEY (`shopping_address_id`),"
					+ "KEY `member_id` (`member_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 45 shopping_car
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `shopping_car` ("
					+ "`shopping_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`member_id` int(11) NOT NULL,"
					+ "`product_id` int(11) NOT NULL,"
					+ "`product_sum` int(11) NOT NULL,"
					+ "PRIMARY KEY (`shopping_id`),"
					+ "KEY `product_id` (`product_id`),"
					+ "KEY `member_id` (`member_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 46 shopping_ads
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `shopping_ads` ("
					+ "`ad_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`ad_title` varchar(100) DEFAULT NULL,"
					+ "`ad_conent` varchar(200) DEFAULT NULL,"
					+ "`ad_name` varchar(100) DEFAULT NULL,"
					+ "`type` int(11) NOT NULL," + "PRIMARY KEY (`ad_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 47 mamber_grade
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `mamber_grade` ("
					+ " `member_grade_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`member_grade_name` varchar(200) DEFAULT NULL,"
					+ "`grade_jifen` int(11) DEFAULT NULL,"
					+ "`grade_image` varchar(500) DEFAULT NULL,"
					+ "PRIMARY KEY (`member_grade_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;");
			// 48 question_rule
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `question_rule` ("
					+ "`question_rule_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`question_num` int(11) DEFAULT '5',"
					+ "`question_tishi` varchar(500) DEFAULT '暂无',"
					+ "`awards` varchar(500) DEFAULT '暂无',"
					+ "`fangqi` varchar(500) DEFAULT '暂无',"
					+ "PRIMARY KEY (`question_rule_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 49 question_tishi
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `question_tishi` ("
					+ "`question_tishi_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`mei_jiang` varchar(500) DEFAULT '暂无',"
					+ "`da_cuo` varchar(500) DEFAULT '暂无',"
					+ "`chao_shi` varchar(500) DEFAULT '暂无',"
					+ "`choujiang` varchar(500) DEFAULT NULL,"
					+ "`daguo` varchar(500) DEFAULT '暂无',"
					+ "`type` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`question_tishi_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制表

			// 限制表 `buy_product`

			stat.executeUpdate("ALTER TABLE `buy_product`"
					+ "ADD CONSTRAINT `buy_product_ibfk_2` FOREIGN KEY (`buy_product_product`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "ADD CONSTRAINT `buy_product_ibfk_1` FOREIGN KEY (`buy_product_order`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 限制表 `order`
			stat.executeUpdate("ALTER TABLE `order`"
					+ " ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`Logistics_id`) REFERENCES `logistics` (`logistics_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " ADD CONSTRAINT `order_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 限制表 `product`
			stat.executeUpdate("ALTER TABLE `product`"
					+ " ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`product_type_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 限制表 `shopping_address`
			stat.executeUpdate("ALTER TABLE `shopping_address`"
					+ " ADD CONSTRAINT `shopping_address_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 限制表 `shopping_car`
			stat.executeUpdate("ALTER TABLE `shopping_car`"
					+ "ADD CONSTRAINT `shopping_car_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "ADD CONSTRAINT `shopping_car_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 限制表 `appraise`
			stat.executeUpdate("ALTER TABLE `appraise`"
					+ " ADD CONSTRAINT `appraise_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " ADD CONSTRAINT `appraise_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 50 fans_user
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `fans_user` ("
					+ " `fans_user_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ " `fans_user_name` varchar(200) DEFAULT NULL,"
					+ "`update_time` varchar(200) DEFAULT NULL,"
					+ "`signature` varchar(500) DEFAULT NULL,"
					+ "`nickname` varchar(200) DEFAULT NULL,"
					+ "`touxiang` varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`fans_user_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 51 fans_image
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `fans_image` ("
					+ "`fans_image_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`fans_image_value` varchar(200) NOT NULL,"
					+ "`image_update_time` varchar(200) NOT NULL,"
					+ "`fans_user_id` int(11) NOT NULL,"
					+ "`check_state` int(11) NOT NULL DEFAULT '0',"
					+ "PRIMARY KEY (`fans_image_id`),"
					+ "KEY `fans_user_id` (`fans_user_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制表 `fans_image`
			stat.executeUpdate("ALTER TABLE `fans_image`"
					+ " ADD CONSTRAINT `fans_image_ibfk_1` FOREIGN KEY (`fans_user_id`) REFERENCES `fans_user` (`fans_user_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 52 fans_image_visit
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `fans_image_visit` ("
					+ "`fans_image_visit_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`fans_visit_key` varchar(200) DEFAULT NULL,"
					+ "`fans_user_name` varchar(200) DEFAULT NULL,"
					+ "`fans_visit_value` varchar(1000) DEFAULT NULL,"
					+ "`fans_image_id` int(11) DEFAULT NULL,"
					+ "`fans_user_id` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`fans_image_visit_id`),"
					+ "KEY `fans_image_id` (`fans_image_id`),"
					+ "KEY `fans_user` (`fans_user_id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 限制表
			stat.executeUpdate("ALTER TABLE `fans_image_visit`"
					+ " ADD CONSTRAINT `fans_image_visit_ibfk_2` FOREIGN KEY (`fans_user_id`) REFERENCES `fans_user` (`fans_user_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ " ADD CONSTRAINT `fans_image_visit_ibfk_1` FOREIGN KEY (`fans_image_id`) REFERENCES `fans_image` (`fans_image_id`) ON DELETE CASCADE ON UPDATE CASCADE;");
			// 53 surquestion
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `surquestion` ("
					+ "`surquestion_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`surquestion_number` varchar(200) NOT NULL,"
					+ "`surquestion_content` varchar(1024) NOT NULL,"
					+ "`surquestion_type` int(11) NOT NULL,"
					+ "`surquestion_addtime` varchar(200) DEFAULT NULL,"
					+ "`surquestion_updatetime` varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`surquestion_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 54 survey
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `survey` ("
					+ "`survey_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`survey_user` varchar(500) CHARACTER SET utf32 NOT NULL,"
					+ "`survey_time` varchar(200) NOT NULL,"
					+ "`survey_code` int(11) NOT NULL,"
					+ "`survey_user_name` varchar(200) NOT NULL,"
					+ "`survey_user_phone` varchar(200) NOT NULL,"
					+ "`survey_user_qq` varchar(200) NOT NULL,"
					+ "`survey_user_email` varchar(200) NOT NULL,"
					+ "`survey_uer_sex` int(11) NOT NULL,"
					+ "`survey_user_age` int(11) NOT NULL,"
					+ "`survey_user_edu` varchar(500) NOT NULL,"
					+ "`survey_user_work` varchar(500) NOT NULL,"
					+ "PRIMARY KEY (`survey_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
			// 55 suroption
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `suroption` ("
					+ "`option_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`option_number` varchar(200) NOT NULL,"
					+ "`option_content` varchar(1024) NOT NULL,"
					+ "`surquestion_id` int(11) NOT NULL,"
					+ "`option_code` int(11) NOT NULL,"
					+ "`option_addtime` varchar(200) DEFAULT NULL,"
					+ "`option_updatetime` varchar(200) DEFAULT NULL,"
					+ "PRIMARY KEY (`option_id`),"
					+ "KEY `surquestion_id` (`surquestion_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 限制表 `suroption`
			stat.executeUpdate("ALTER TABLE `suroption`"
					+ "ADD CONSTRAINT `suroption_ibfk_1` FOREIGN KEY (`surquestion_id`) REFERENCES `surquestion` (`surquestion_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 56 surrecord
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `surrecord` ("
					+ "`surrecord_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`surquestion_id` int(11) NOT NULL,"
					+ "`option_id` int(11) NOT NULL,"
					+ "`survey_id` int(11) NOT NULL,"
					+ "PRIMARY KEY (`surrecord_id`),"
					+ " KEY `surquestion_id` (`surquestion_id`),"
					+ " KEY `option_id` (`option_id`),"
					+ " KEY `survey_id` (`survey_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 限制表 `surrecord`
			stat.executeUpdate("ALTER TABLE `surrecord`"
					+ "ADD CONSTRAINT `surrecord_ibfk_3` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`survey_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "ADD CONSTRAINT `surrecord_ibfk_1` FOREIGN KEY (`surquestion_id`) REFERENCES `surquestion` (`surquestion_id`) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "ADD CONSTRAINT `surrecord_ibfk_2` FOREIGN KEY (`option_id`) REFERENCES `suroption` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 57 fands_greeting_card
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `fands_greeting_card` ("
					+ "`fands_card_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ " `fromUser` varchar(100) NOT NULL,"
					+ "`card_id` int(11) NOT NULL,"
					+ "`card_value` longtext NOT NULL,"
					+ "PRIMARY KEY (`fands_card_id`),"
					+ "KEY `card_id` (`card_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 58 greeting_card
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `greeting_card` ("
					+ "`card_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`card_template_id` int(11) NOT NULL,"
					+ "`card_value` longtext NOT NULL,"
					+ "`card_bg_image` varchar(100) NOT NULL,"
					+ "`card_txt_image` varchar(100) NOT NULL,"
					+ "`card_title_image` varchar(200) DEFAULT NULL,"
					+ "`card_name` varchar(200) NOT NULL,"
					+ "PRIMARY KEY (`card_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			stat.executeUpdate("ALTER TABLE `fands_greeting_card`"
					+ "ADD CONSTRAINT `fands_greeting_card_ibfk_1` FOREIGN KEY (`card_id`) REFERENCES `greeting_card` (`card_id`) ON DELETE CASCADE ON UPDATE CASCADE;");

			// 59 greeting_card_type
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `greeting_card_type` ("
					+ "`card_type_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`card_type_name` varchar(200) NOT NULL,"
					+ "`card_type_state` int(11) NOT NULL,"
					+ "PRIMARY KEY (`card_type_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");

			// 60
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `greeting_card_proterty` ("
					+ " `card_proterty_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ " `guide_url` varchar(200) DEFAULT NULL,"
					+ "`card_id` int(11) DEFAULT NULL,"
					+ "PRIMARY KEY (`card_proterty_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;");
			// 61 shiyong
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS `shiyong` ("
					+ "`shiyong_id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "`shiyong_name` varchar(100) DEFAULT NULL,"
					+ "`shiyong_value` varchar(100) NOT NULL DEFAULT '0',"
					+ "PRIMARY KEY (`shiyong_id`)"
					+ ") ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;");

			stat.executeUpdate("INSERT INTO `shiyong` (`shiyong_id`, `shiyong_name`, `shiyong_value`) VALUES"
					+ "(1, '天气', '0'),"
					+ "(2, '空气', '0'),"
					+ "(3, '手机', '0'),"
					+ "(4, '快递', '0'),"
					+ "(5, ' 火车', '0'),"
					+ "(6, '导航', '0'),"
					+ "(7, '周边', '0'),"
					+ "(8, '公交', '0'),"
					+ "(9, '成语', '0'),"
					+ "(10, '释义', '0'),"
					+ "(11, '身份', '0')," + "(12, '身高', '0');");

			// 关闭数据库
			stat.close();
			conn.close();
			System.out.println("连接创建成功");
			f = true;
		} catch (Exception e) {
			// TODO: handle exception
			f = false;
			System.out.println("连接、创建失败");

			e.printStackTrace();

		}
		return f;
	}
	/*
	 * public static void main(String[] args) { CreateDatabase a=new
	 * CreateDatabase(); a.createDateBase("wxpt10"); }
	 */
}
