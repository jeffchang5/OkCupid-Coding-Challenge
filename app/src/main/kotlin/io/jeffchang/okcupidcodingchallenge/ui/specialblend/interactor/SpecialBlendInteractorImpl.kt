package io.jeffchang.okcupidcodingchallenge.ui.specialblend.interactor

import io.jeffchang.okcupidcodingchallenge.data.local.dao.MatchDao
import io.jeffchang.okcupidcodingchallenge.data.model.Match
import io.jeffchang.okcupidcodingchallenge.data.remote.MatchService
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Implementation of special blend interactor.
 */
class SpecialBlendInteractorImpl constructor(private val matchService: MatchService,
                                             private val matchDao: MatchDao)
    : SpecialBlendInteractor {

    data class CachedMatch(val isCached: Boolean, val matches: ArrayList<Match>)

    override fun insertMatchToDb(isLiked: Boolean, match: Match): Single<Unit> {
        match.liked = isLiked
        return Single.fromCallable {
            matchDao.insert(match)
        }
    }

    override fun insertMatchesToDb(matches: ArrayList<Match>): Single<Unit>  {
        return Single.fromCallable {
            matchDao.insertListOfMatches(matches)
        }
    }

    override fun getMatches(): Observable<CachedMatch> =
            getMatchesFromDb().flatMap {
                if (it.isEmpty())
                    getMatchesFromAPI().map {
                        CachedMatch(false, ArrayList(it)) }
                else Observable.just(CachedMatch(true, ArrayList(it)))
            }
    
    override fun getMatchesFromDb(): Observable<List<Match>> = matchDao.getMatches().toObservable()


    override fun getMatchesFromAPI(): Observable<List<Match>>
            = matchService.getMatch().map { it.data }
}