/**
 * 
 */
package com.internship.sms.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Thu Soe San
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	
	private boolean status = true;
	private Object data ;
	private String message;
}
