import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  @ViewChild('map', {static: true}) mapElement: ElementRef;

  zoom = 12
  center: google.maps.LatLngLiteral
  options: google.maps.MapOptions = {
    mapTypeId: 'hybrid',
    zoomControl: false,
    scrollwheel: false,
    disableDoubleClickZoom: true,
    maxZoom: 15,
    minZoom: 8,
  }

  constructor() { }



  ngOnInit(): void {



    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      }
    })
  //   var map: google.maps.Map;
  //   const mapProperties = {
  //        center: new google.maps.LatLng(39.7392,-104.9903),
  //        zoom: 15,
  //        mapTypeId: google.maps.MapTypeId.ROADMAP

  //   };
  //   var marker = new google.maps.Marker({
  //     position: new google.maps.LatLng(39.7392,-104.9903),
  //     map: map,
  //  });
  //   map = new google.maps.Map(this.mapElement.nativeElement,   mapProperties);
 }

 zoomIn() {
  if (this.zoom < this.options.maxZoom) this.zoom++
}

zoomOut() {
  if (this.zoom > this.options.minZoom) this.zoom--
}
}

// <script>
// 		var geocoder;
// 		var map;
// 		var address = "${meetup.address.street}, ${meetup.address.city}, ${meetup.address.stateAbbr}, ${meetup.address.postalCode}";
// 		function initMap() {
// 			var map = new google.maps.Map(document.getElementById('map'), {
// 				zoom : 15,
// 				center : {
// 					lat : 39.7392,
// 					lng : -104.9903
// 				}
// 			});
// 			geocoder = new google.maps.Geocoder();
// 			codeAddress(geocoder, map);
// 		}

// 		function codeAddress(geocoder, map) {
// 			geocoder
// 					.geocode(
// 							{
// 								'address' : address
// 							},
// 							function(results, status) {
// 								if (status === 'OK') {
// 									map.setCenter(results[0].geometry.location);
// 									var marker = new google.maps.Marker({
// 										map : map,
// 										position : results[0].geometry.location
// 									});
// 								} else {
// 									alert('Geocode was not successful for the following reason: '
// 											+ status);
// 								}
// 							});
// 		}
// 	</script>
// 	<script async defer
// 		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDzy-u0ZqxrRptKkEvuJV7nUwWazekSszQ&callback=initMap">

// 	</script>
