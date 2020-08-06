/**
 * Date: 2020-08-02 14:51
 * Author: xupp
 */

package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @JsonIgnore
    private String id;

    private  String name;

    private  Integer age;
}
