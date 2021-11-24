package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.commands.ChangeVersionsStrategyCommand;
import controller.commands.DisableVersionsManagementCommand;
import controller.commands.EditCommand;
import controller.commands.EnableVersionsManagementCommand;
import controller.commands.RollbackToPreviousVersionCommand;
import controller.commands.SaveCommand;
import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import model.strategies.VersionsStrategy;
import model.strategies.VersionsStrategyFactory;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;

class VersionsManagerTest {

	private static DocumentManager documentManager;
	private static VersionsManager versionsManager;
	private static LatexEditorView editorView;
	
	private VersionsStrategyFactory versionsFactory = new VersionsStrategyFactory();
	
	@BeforeAll
	public static final void setUp() {
		editorView = LatexEditorView.getInstance();
		documentManager = DocumentManager.getInstance();
		versionsManager = VersionsManager.getInstance();
		
	}
	
	
	// US-4 volatile
	@Test
	public final void testVolatileStrategy() {
		// Create document
		Document document = documentManager.createDocument("bookTemplate");
		
		editorView.setCurrentDocument(document);
		editorView.setText(document.getContents());
		
		// Create strategy
		VersionsStrategy volatileStrategy = versionsFactory.createStrategy("volatileStrategy");
		versionsManager.setStrategy(volatileStrategy);
		editorView.setStrategy("volatile");
		
		// Enable Strategy
		EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand();
		enableCommand.execute();
		
		
		// Save file so that we have a version saved
		// we also test that the version mechanism has been enabled
		EditCommand editCommand = new EditCommand();
		editCommand.execute();
		
		assertEquals(document.getContents(), volatileStrategy.getVersion().getContents());		
		
	}
	
	// US-4 stable
	@Test
	public final void testStableStrategy() {
		// Create document
		Document document = documentManager.createDocument("bookTemplate");
		
		editorView.setCurrentDocument(document);
		editorView.setText(document.getContents());
		
		// Create strategy
		VersionsStrategy stableStrategy = versionsFactory.createStrategy("stableStrategy");
		versionsManager.setStrategy(stableStrategy);
		editorView.setStrategy("stable");
		
		// Enable Strategy
		EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand();
		enableCommand.execute();
			
		// Save file so that we have a version saved
		// we also test that the version mechanism has been enabled
		EditCommand editCommand = new EditCommand();
		editCommand.execute();
		
		assertEquals(document.getContents(), stableStrategy.getVersion().getContents());			
	}
	
	

	
	// US-5 from Stable to Volatile
	@Test
	public final void testChangeVersionManager1() {
		
		// Create strategy
		editorView.setStrategy("stable");
		
		// Enable Strategy
		EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand();
		enableCommand.execute();
		
		
		
		VersionsStrategy volatileStrategy = versionsFactory.createStrategy("volatileStrategy");
		versionsManager.setStrategy(volatileStrategy);
		
		// Change Strategy 
		ChangeVersionsStrategyCommand changeCommand = new ChangeVersionsStrategyCommand();
		changeCommand.execute();
		
		assertNotEquals(volatileStrategy, versionsManager.getStrategy());
		
	}
	
	// US-5 from Volatile to Stable
	@Test
	public final void testChangeVersionManager2() {
		
		// Create strategy
		editorView.setStrategy("volatile");
		
		// Enable Strategy
		EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand();
		enableCommand.execute();
		
		
		VersionsStrategy stableStrategy = versionsFactory.createStrategy("stableStrategy");
		versionsManager.setStrategy(stableStrategy);
		
		// Change Strategy 
		ChangeVersionsStrategyCommand changeCommand = new ChangeVersionsStrategyCommand();
		changeCommand.execute();
		
		assertNotEquals(stableStrategy, versionsManager.getStrategy());
			
	}
	
	// US-6 disable version tracking
	@Test
	public final void testDisableVersionManager() {
		
		DisableVersionsManagementCommand disableCommand = new DisableVersionsManagementCommand();
		disableCommand.execute();
		
		assertEquals(versionsManager.isEnabled(), false);
	}
	
	@Test
	public final void testRollbackVersionManager() {
		// Create document
		Document document = documentManager.createDocument("bookTemplate");
		editorView.setCurrentDocument(document);
		String firstContents = document.getContents();
		editorView.setText(firstContents);
		EditCommand editCommand = new EditCommand(); 
		
		
		// Create strategy
		editorView.setStrategy("volatile");
		
		// Enable Strategy
		EnableVersionsManagementCommand enableCommand = new EnableVersionsManagementCommand();
		enableCommand.execute();
		VersionsStrategy volatileStrategy = versionsFactory.createStrategy("volatileStrategy");
		versionsManager.setStrategy(volatileStrategy);
		editCommand.execute();
		//After enabling Versions we change text
		document.setContents("test rollback");
		editorView.setText(document.getContents());
		
		// Save 
		
		editCommand.execute();
		
		RollbackToPreviousVersionCommand rollbackCommand = new RollbackToPreviousVersionCommand();
		rollbackCommand.execute();
		document = editorView.getCurrentDocument();
		assertEquals(firstContents, document.getContents());
		
		
		
	}
	
	
	
	
	
	

}
