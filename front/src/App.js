import React from 'react';
import { StoreProvider } from "./store";
import List from "./lists/ListGroup";
import Form from "./forms/FormGroup";

function App() {
  return <StoreProvider>
    <h3>To-Do List</h3>
    <Form />
    <List />
  </StoreProvider>
}

export default App;
