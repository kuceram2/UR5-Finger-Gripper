package sps.gripper.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class gripperProgramNodeService implements SwingProgramNodeService<gripperProgramNodeContribution, gripperProgramNodeView>{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "Gripper URCap";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "Gripper";
	}

	@Override
	public gripperProgramNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new gripperProgramNodeView(apiProvider);
	}

	@Override
	public gripperProgramNodeContribution createNode(ProgramAPIProvider apiProvider, gripperProgramNodeView view,
			DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new gripperProgramNodeContribution(apiProvider, view, model);
	}

	
}