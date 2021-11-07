package co.fullstacklabs.cuboid.challenge.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing Bags table
 *
 * @author FullStack Labs
 * @version 1.0
 * @since 2021-10-22
 */
@Entity
@Table(name = "BAGS")
@Getter
@Setter
@NoArgsConstructor
public class Bag {
    public static final int TITLE_MAX_SIZE = 100;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "VOLUME", nullable = false)
    private double volume;
    @Column(name = "TITLE", nullable = false, length = TITLE_MAX_SIZE)
    private String title;
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true, mappedBy = "bag")
    @Setter(AccessLevel.PRIVATE)
    private List<Cuboid> cuboids = new ArrayList<>();

    public Bag(String title, double volume) {
        this.setVolume(volume);
        this.setTitle(title);
    }

    /**
     * Returns an unmodifiable List containing the cuboids elements.
     *
     * @return List<Cuboid>
     */
    public List<Cuboid> getCuboids() {
        return List.copyOf(cuboids);
    }

    /**
     * Adds a cuboid on the bag, additionally calculates the volume of the bag for each new cuboid.
     * @param cuboid The {@link Cuboid} to add to this bag, cannot be null.
     */
    public void addCuboid(Cuboid cuboid) {
        if (cuboid != null) {
            cuboids.add(cuboid);
        }
    }

    /**
     * Calculates if the cuboid can be added to the bag.
     * @param cuboid The cuboid to add.
     * @return True if the cuboid can be added, false otherwise.
     */
    public boolean canHold(Cuboid cuboid) {
        if (cuboid != null) {
            double payloadVolume = cuboids.stream().mapToDouble(Cuboid::getVolume).sum();
            return volume - payloadVolume >= 0;
        }
        return false;
    }
}
