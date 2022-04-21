# Docker Commands 





--->  Create a docker image using docker file 

-----------------------------------------------
    

    example : docker images   -- list of images

------------------------------------------------------
  
---> Build a docker image using docker file
  
    docker build -t <imagename> .  -- build 
    ex: - docker build -t userservice . 
-----------------------------------------------

--->   Pushing an img to docker hub 

    (i) open cmd -> 
    docker login -> enter login deatils --> after login ->

    (ii)docker tag <imagename>:<TAG> <username/reponame>/<imagename>:optional[<version>]
    Example : 
    docker tag userservice:latest rameswarakonakalla/userservice

    (iii)docker push <imagenamewithrepo/username>
    example
    docker push rameswarakonakalla/userservice
  
  -----------------------------------------------

--->  To run a docker image 

    run docker image(9090 container port , 8080 local port)

    docker run -p 8082:8082 <imagename> 

-------------------
docker.io/rameswarakonakalla/userservice
