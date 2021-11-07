package co.fullstacklabs.cuboid.challenge.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuboidDTO {
    private Long id;

    @NotNull(message = "Cuboid width can't be null.")
    private Float width;

    @NotNull(message = "Cuboid height can't be null.")
    private Float height;

    @NotNull(message = "Cuboid depth can't be null.")
    private Float depth;

    @NotNull(message = "Cuboid related bag can't be null.")
    private Long bagId;

    private Double volume;

    /**
     * Calculates this cuboid volume.
     *
     * @return The calculated volume of this cuboid.
     */
    public Double getVolume() {
        if (width != null && height != null && depth != null) {
            return (double) (width * height * depth);
        }
        return 0d;
    }

}
