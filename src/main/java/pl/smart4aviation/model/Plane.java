package pl.smart4aviation.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Plane {
    private int maxPassengers;
    private boolean isActive;

    public Plane(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        this.isActive = true;
    }
}
