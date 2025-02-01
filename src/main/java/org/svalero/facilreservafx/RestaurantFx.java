package org.svalero.facilreservafx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantFx {
    private long id;
    private String name;
    private String address;
    private int capacity;
    private boolean available;
}