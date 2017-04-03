package model.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	VoteSuggestionTest.class,
	VoteCommentaryTest.class,
	ParticipantTest.class
})
public class AllTest {

}