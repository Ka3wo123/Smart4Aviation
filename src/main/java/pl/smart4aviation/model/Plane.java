package pl.smart4aviation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
