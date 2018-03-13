package restaurant;

import restaurant.model.Vote;

import static java.time.LocalDateTime.of;

import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {
    public static final int VOTE_ID1 = 100050;
    public static final int VOTE_ID2 = 100051;
    public static final int VOTE_ID3 = 100052;

    public static final Vote VOTE1=new Vote(VOTE_ID1, of(2018, Month.FEBRUARY, 20, 10, 0), 1);
    public static final Vote VOTE2=new Vote(VOTE_ID2, of(2018,Month.FEBRUARY,20, 10, 0),-1);
    public static final Vote CREATEVOTE=new Vote(of(2019,Month.FEBRUARY,21,10,22,22),1);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurants","user");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurants","user").isEqualTo(expected);
    }


}
