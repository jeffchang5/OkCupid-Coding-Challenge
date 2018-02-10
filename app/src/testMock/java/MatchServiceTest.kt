import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupiddemo.dagger.modules.data.MockMatchService
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by jeffreychang on 2/9/18.
 */

class MatchServiceTest {

    private lateinit var match: List<Match?>

    private lateinit var matchItem: Match

    @Before
    fun initializeMatchService() {
        val matchResponse = MockMatchService()
                .getMatch().blockingGet()
        match = matchResponse.data
        matchItem = matchResponse.data[0]
    }

    @Test
    fun matchServiceResponse_isNotEmpty() {
        assertTrue(match.isNotEmpty())
    }

    @Test
    fun matchServiceResponse_hasNonNullFields() {
        assertNotNull(matchItem.age)
        assertNotNull(matchItem.userid)
        assertNotNull(matchItem.isOnline)
        assertNotNull(matchItem.enemy)
    }
}