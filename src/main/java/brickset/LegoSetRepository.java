package brickset;

import repository.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * @param name the name of a LegoSet
     * @return the boolean value of the predicament that a LegoSet exists or not with such name
     */

    public boolean doesLegoSetWithNameExist(String name)
    {
        return getAll().stream().
                anyMatch(s -> s.getName().equals(name));
    }

    /**
     *
     * @return the int value of the sum of all LegoSet pieces with the pieces value being over 1000
     */

    public int sumOfAllLegoSetPiecesOfSetsWithMoreThanAThousandPieces()
    {
        return getAll().stream()
                .map(LegoSet::getPieces)
                .filter(pieces -> pieces > 1000)
                .reduce(0, Integer::sum);
    }

    /**
     *
     * @return the number of distinct tags
     */
    public long countDistinctLegoSetTags() {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() !=null)
                .flatMap(legoset -> legoset.getTags().stream())
                .distinct()
                .count();
    }

    /**
     *
     * @return a Map containing LegoSet number and brickSetURL pairs
     */
    public Map getLegoSetNumberAndBrickSetUrlPairs() {
        return getAll().stream()
                .collect(Collectors.toMap(LegoSet::getNumber, LegoSet::getBricksetURL));
    }


    /**
     *
     * @return a Map containing PackigingTypes with their corresponding count values in pairs
     */
    public Map<PackagingType, Long> countAppearanceOfEachPackigingType() {
        return getAll().stream()
                .collect(Collectors.groupingBy(LegoSet::getPackagingType,Collectors.counting()));
    }




    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.doesLegoSetWithNameExist("Monster 4"));
        System.out.println(repository.countDistinctLegoSetTags());
        System.out.println(repository.sumOfAllLegoSetPiecesOfSetsWithMoreThanAThousandPieces());
        System.out.println(repository.getLegoSetNumberAndBrickSetUrlPairs());
        System.out.println(repository.countAppearanceOfEachPackigingType());

    }

}
