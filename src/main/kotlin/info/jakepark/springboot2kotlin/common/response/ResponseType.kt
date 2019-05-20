package info.jakepark.springboot2kotlin.common.response

import org.springframework.http.HttpStatus

/*
    // type offset : 1000
    // default offset in same type : 10
 */
enum class ResponseType(val code: String, val httpStatus: HttpStatus, val message: String) {

    //------------------------------------------------------------------------------------
    // [ 0000 ] common success
    //------------------------------------------------------------------------------------
    OK("0000", HttpStatus.OK, "request.success"),
    OK_BUT_NO_CONTENT("0010", HttpStatus.NO_CONTENT, "request.noContent"),

    //------------------------------------------------------------------------------------
    // [ 1000 ~ 1999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 2000 ~ 2999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 3000 ~ 3999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 4000 ~ 4999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 5000 ~ 5999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 6000 ~ 6999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 7000 ~ 7999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 8000 ~ 8999 ]
    //------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------
    // [ 9000 ~ 9999 ] common error
    //------------------------------------------------------------------------------------
    BAD_REQUEST("9000", HttpStatus.BAD_REQUEST, "request.badRequest"),
    NOT_FOUND("9001", HttpStatus.NOT_FOUND, "request.not.found"),
    INTERNAL_SERVER_ERROR("9999", HttpStatus.INTERNAL_SERVER_ERROR, "internal.server.error"),
    ;

}