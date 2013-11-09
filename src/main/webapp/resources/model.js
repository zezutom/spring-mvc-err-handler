function Person(data) {
	this.id = ko.observable(data.id);
	this.name = ko.observable(data.name);
};

function PersonViewModel() {
	var self = this;
	self.people = ko.observableArray([]);
	self.byId = ko.observable();
	self.output = ko.observable();
	self.error = ko.observable();
	
	this.search = function() {
		var query = "person";
		var byId = self.byId();
		
		if (byId)
			query += "/" + byId;
		
		var output = self.output();
		
		if (output === 'html') {
			location.href = query;
		}
		else {
			query += '/json';
			$.ajax({
				url: query,
				dataType: 'json',
				success: function(data) {
					var people = $.map(data, function(item) {return new Person(item);});
					self.people(people);	
					self.error(null);
				},
				error: function(data) {
					var error = $.parseJSON(data.responseText);
					self.error(error.message);
					self.people([]);
				}
			});			
		}			
	};
};
ko.applyBindings(new PersonViewModel());