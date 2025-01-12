package pl.smart4aviation.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class Plane {
    private int maxPassengers;
    private boolean isActive;

    public Plane(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        this.isActive = true;
    }
}
