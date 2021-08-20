package com.jbase.app

import groovy.json.JsonSlurper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @Title
 * @Description
 * @author cWww
 * @date: 2019/4/4  14:03
 */
//println "hello,world"

//println String.valueOf((Long)Math.pow(10.0,10.0))


//println Integer.valueOf(10).intValue()

//str = "aaa,"
//
//println str.split(",")

//println "123".substring()
//"123".equals()

//println String.valueOf(111)
//
//println "江苏双酿医疗器械有限公司 该名称2".toLowerCase()
//
//println Math.sqrt(3.0)
//
//groovy.json.JsonOutput
//
//def array = [["name":"aaa"],["name":"bbb"]]
//
//println array.size()

//new  JsonSlurper

//try
//{
//    Document document = Jsoup.connect("http://www.yiibai.com").get()
//    println document.title()
//}
//catch (IOException e)
//{
//    e.printStackTrace()
//}
sdf = new SimpleDateFormat("yyyyMMdd")
sdf.setLenient(false)

date = sdf.parse("00000000")
println date
println sdf.format(date)

//DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd")

//println LocalDateTime.parse("00010101",DATE_TIME_FORMATTER)
