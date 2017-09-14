package fdi.maps.client;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;




public class GMapsEJ implements EntryPoint {
	
	private static GreetingServiceAsync serviceActions = GWT.create(GreetingService.class);
	private Map<String, List<String>> ListaParam;
	private VerticalPanel Params;
	
	public void onModuleLoad() {

		//Captura de Parametros http://localhost:8080/GMapsURL/?latitude=36.5008762&longitude=-6.2684345
		
		 Map<String, List<String>> listaParam = com.google.gwt.user.client.Window.Location.getParameterMap();
		
		 VerticalPanel General=new VerticalPanel();
		 
		 RootPanel.get("centered").add(General);
		 
		 ListaParam=listaParam;
		 
		 General.setSpacing(5);
		 
		 General.add(new Label("Parameters"));
		 
		 Params=new VerticalPanel();
		 General.add(Params);
		 
		 Params.setSpacing(5);

		 serviceActions.getParams(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error");
				
			}

			@Override
			public void onSuccess(List<String> result) {
				for (String valorPar : result) {
					HorizontalPanel PanelParam=new HorizontalPanel();
					Params.add(PanelParam);
					PanelParam.add(new Label(valorPar));
					VerticalPanel ValuesV=new VerticalPanel();
					PanelParam.add(ValuesV);
					if (ListaParam.get(valorPar)!=null)
					for (String value : ListaParam.get(valorPar)) {
						TextArea T = new TextArea();
						T.setText(value);
						ValuesV.add(T);
					}
					else
						ValuesV.add(new TextArea());
						
				}
				
			}
		});
		 
for (Entry<String, List<String>> widget : listaParam.entrySet()) {
	
}		 
		 
		   
        
	}

	
}