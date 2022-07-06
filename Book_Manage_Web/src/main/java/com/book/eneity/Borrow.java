package com.book.eneity;

import lombok.Data;

import java.util.Date;

/**
 * @author Wahoyu
 * @date 2022/7/4
 */
@Data
public class Borrow {
    int id;
    int book_id;
    String book_name;
    Date time;
    String student_name;
    int student_id;
}
