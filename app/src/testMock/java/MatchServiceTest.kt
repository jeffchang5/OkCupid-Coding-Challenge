import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.di.modules.data.MockMatchService
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Tests Mock Service.
 */

class MatchServiceTest {

    private lateinit var match: List<Match?>

    private lateinit var matchItem: Match

    /**
     * Gets a match object from our mock service
     */
    @Before
    fun initializeMatchService() {
        val matchResponse = MockMatchService()
                .getMatch().blockingFirst()
        match = matchResponse.data
        matchItem = matchResponse.data[0]
    }

    /**
     * Tests if match is not empty.
     */
    @Test
    fun matchServiceResponse_isNotEmpty() {
        assertTrue(match.isNotEmpty())
    }

    /**
     * Tests if fields in match object are not null.
     */
    @Test
    fun matchServiceResponse_hasNonNullFields() {
        assertNotNull(matchItem.age)
        assertNotNull(matchItem.userid)
        assertNotNull(matchItem.isOnline)
        assertNotNull(matchItem.enemy)
    }
}