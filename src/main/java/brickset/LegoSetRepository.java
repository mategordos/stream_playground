package brickset;

import repository.Repository;

import java.util.List;
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
                anyMatch(s -> s.getName().toString().contains(name));
    }
/*
    public List<LegoSet> sumOfAllLegoSetPiecesWithMoreThanAThousandPieces()
    {
        return getAll().stream()
                .filter(legoSet -> legoSet.getPieces() > 1000)
                .reduce();
    }

*/



    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.doesLegoSetWithNameExist("Monster 4"));
 //       System.out.println(repository.sumOfAllLegoSetPiecesWithMoreThanAThousandPieces());
    }

}
