package co.fullstacklabs.cuboid.challenge.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

import co.fullstacklabs.cuboid.challenge.model.Bag;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BagDTO {
    private Long id;

    @NotNull(message = "Bag volume can't be null.")
    private Double volume;

    @NotNull(message = "Bag title can't be null.")
    @Size(min = 1, max = Bag.TITLE_MAX_SIZE, message = "Bag title maximum size is " + Bag.TITLE_MAX_SIZE + " characters.")
    private String title;
    private List<CuboidDTO> cuboids;

    /**
     * Calculates the payload (cuboids) total volume.
     * @return payload (cuboids) total volume.
     */
    public Double getPayloadVolume() {
        return cuboids.stream().mapToDouble(CuboidDTO::getVolume).sum();
    }

    /**
     * Calculates the available space in the bag.
     * @return available space in the bag.
     */
    public Double getAvailableVolume() {
        return volume - getPayloadVolume();
    }
}
