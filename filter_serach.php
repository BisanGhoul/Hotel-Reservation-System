<?php




		$server_name = "localhost";
		$username = "root";
		$password = "";
		$dbname = "hotel_system";
		
		$conn = new mysqli($server_name, $username, $password, $dbname);
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		} 
		$sql = "SELECT * FROM room_amenties,room where"." room_amenties.RNO=room.RNO  "  ;
		
		
		$result = $conn->query($sql);
		$resultarray = array();
		while($row =mysqli_fetch_assoc($result))
		{
			$resultarray[] = $row;
		}
		echo json_encode($resultarray);
		
		$conn->close();
		
	
	
	


	http://10.0.2.2:84/mobileproject/filtersearch.php?floor=2

?>