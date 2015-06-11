package lendmanager.mongo.rest;

import lendmanager.SampleDatabaseCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/test")
public class RestTestController {
	
	@Autowired
	private SampleDatabaseCreator creator;
	
	@RequestMapping(value="/addData", method=RequestMethod.GET)
	public void addSample() {
		creator.addData();
	}
}
