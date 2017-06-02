/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luizalabs.friendsserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alanfrank
 */
public class Util {
     /**
     * Método responsável por converter String em Date
     *
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public Date toDate(String str, String pattern) throws ParseException {
        java.util.Date res = null;
        res = new SimpleDateFormat(pattern).parse(str.replace("'", ""));
        return res;
    }
}
