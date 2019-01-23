/**
 * 
 */
package com.bdqn.spz.springbootmaven.config;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler
{
   @ExceptionHandler(value = Exception.class)
   public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e)
         throws Exception
   {
      return createModelAndView(request, "500", HttpStatus.INTERNAL_SERVER_ERROR, e);
   }

   private ModelAndView createModelAndView(HttpServletRequest request, String viewName, HttpStatus status, Exception e)
   {
      ModelAndView mav = new ModelAndView();
      if (e != null)
      {
         mav.addObject("error", e);
      }
      mav.addObject("url", request.getRequestURI());
      mav.addObject("method", request.getMethod());
      if (status != null)
      {
         mav.setStatus(status);
      }
      mav.setViewName(viewName);
      return mav;
   }
}
