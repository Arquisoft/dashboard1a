package domainModel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ComentarTest.class, 
	ProponerTest.class,
	VotarCommentaryTest.class,
	VotarSuggestionTest.class
})
public class AllTest {

}
