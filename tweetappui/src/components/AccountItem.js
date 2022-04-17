import React from "react";
import { Hashicon } from "@emeraldpay/hashicon-react";
import {Row } from "react-bootstrap";

function AccountItem(props) {

  if(props.profile!=="yes"){
  return (
    <div className="border shadow rounded-3 border-primary p-3 mt-3">
      <Row>
        <div className="d-flex align-items-center mb-3">
          <div className="mx-3">
            <Hashicon value={props.username} size={50} />
          </div>
          <div className="d-flex flex-column">
          <div className="fw-bold">{props.firstName + " " + props.lastName}</div>
          
          </div>
        </div>
        <div className="mx-3">
          <div>
            <h6>{props.username}</h6>
            <p>{props.contact}</p>
          </div>
        </div>
        </Row>
        </div>
    
  );
  }
  else{
    return (
      <div className="border shadow rounded-3 border-primary p-3 mt-3">
        <Row>
          <div className="d-flex align-items-center mb-3">
            <div className="mx-3">
              <Hashicon value={props.username} size={50} />
            </div>
            <div className="d-flex flex-column">
            <div className="fw-bold">{props.firstName + " " + props.lastName}</div>
            
            </div>
          </div>
          <div className="mx-3">
            <h6><b>First Name: </b>
            {props.firstName}
            &emsp;
            &emsp;
            &emsp;
            <b>Last Name: </b>
            {props.lastName}
            </h6>
          </div>
          <div className="mx-3">
              <h6><b>Email: </b>{props.username}</h6>
          </div>
          <div className="mx-3">
              <h6><b>Contact No: </b>{props.contact}</h6>
          </div>
          </Row>
          </div>
      
    );
  }
}

export default AccountItem;
