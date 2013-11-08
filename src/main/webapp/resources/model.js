function Person(data) {
	this.id = ko.observable(data.id);
	this.name = ko.observable(data.name);
};

function PersonViewModel() {
	var self = this;
	self.people = ko.observableArray([]);
	self.byId = ko.observable();
	
	this.search = function() {
		var query = "person";
		var byId = self.byId();
		
		if (byId)
			query += "/" + byId;
				
		$.getJSON(query, function(data) {
			// TODO: catch failed responses (i.e. status code != HTTP 200)
			var people = $.map(data, function(item) {return new Person(item);});
			self.people(people);
		});
	};
};
ko.applyBindings(new PersonViewModel());