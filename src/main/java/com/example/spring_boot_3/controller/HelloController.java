package com.example.spring_boot_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {
  
  @RequestMapping()
  public String index( Model model ) {
    model.addAttribute( "msg", "これはコントローラに用意したメッセージです。" );
    return "index";
  }
  
  @RequestMapping( "/{num}" )
//  public String index( @PathVariable int num, Model model ) {
    public String index( Model model, @PathVariable int num ) {
    int res = 0;
    for ( int i = 1; i <= num; i++ ) {
      res += i;
    }
    model.addAttribute( "msg", "total: " + res );
    return "index";
  }
  
  @RequestMapping( "/mav/{num}" )
  public ModelAndView index( @PathVariable int num, ModelAndView mav ) {
    int total = 0;
    for ( int i = 1; i <= num; i++ ) {
      total += i;
    }
    mav.addObject( "msg", num + "までの合計を計算します。" );
    mav.addObject( "content", "total: " + total );
    mav.setViewName( "index" );
    return mav;
  }
  
  @RequestMapping( value="/form", method=RequestMethod.GET )
  public ModelAndView index( ModelAndView mav ) {
    mav.addObject( "msg", "名前を書いてください。" );
    mav.setViewName( "form" );
    return mav;
  }
  
  @RequestMapping( value="/form", method=RequestMethod.POST )
  public ModelAndView index( @RequestParam( "text1" ) String str, ModelAndView mav ) {
    mav.addObject( "msg", "こんにちは" + str + "さん！" );
    mav.addObject( "value", str );
    mav.setViewName( "form" );
    return mav;
  }
  
  @RequestMapping( value="/form2", method=RequestMethod.GET )
  public ModelAndView index2( ModelAndView mav ) {
    mav.setViewName( "form2" );
    mav.addObject( "msg", "フォームを送信ください。" );
    return mav;
  }
  
  @RequestMapping( value="/form2", method=RequestMethod.POST )
  public ModelAndView index2(
      @RequestParam( value="check1", required=false ) boolean check1,
      @RequestParam( value="radio1", required=false ) String radio1,
      @RequestParam( value="select1", required=false ) String select1,
      @RequestParam( value="select2", required=false ) String[] select2,
      ModelAndView mav ) {
    
    String res = "";
    try {
      res = "check: " + check1 +
        " radio: " + radio1 +
        " select1: " + select1 +
        "<br/>select2: ";
    } catch( NullPointerException e ) {}
    try {
      res += select2[ 0 ];
      for ( int i = 1; i < select2.length; i++ ) {
        res += ", " + select2[ i ];
      }
    } catch( NullPointerException e ) {
      res += "null";
    }
    
    mav.setViewName( "form2" );
    mav.addObject( "msg", res );
    return mav;
  }
  
}
