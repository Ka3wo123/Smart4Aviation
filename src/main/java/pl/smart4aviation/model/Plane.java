package pl.smart4aviation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Plane {
    private int maxPassengers;
    private boolean isActive;

    public Plane(int maxPassengers) {
        this.maxPassengers = maxPassengers;
        this.isActive = true;
    }
}
