/**  

* <p>Title: MyException.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月7日 上午9:17:39 

* @version 1.0  

*/
package org.Father.COMMON.exception;

/**  

* <p>Title: MyException</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月7日 上午9:17:39 

*/
public class MyException extends RuntimeException{
	
	public MyException(String message) {
        super(message);
    }
	
	public MyException(String message, Throwable cause) {
        super(message, cause);
    }

}
