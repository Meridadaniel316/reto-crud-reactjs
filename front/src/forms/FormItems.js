import React, { useContext, useRef, useState } from 'react';
import consumer from "../tools/consumer";
import events from "../tools/events";
import Store from "../store";

export default ({ listId, todo }) => {
    const formRef = useRef(null);
    const { dispatch } = useContext(Store);
    const item = todo.item[listId] ? todo.item[listId] : {};
    const [state, setState] = useState(item);

    const onAdd = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id: null,
            completed: false
        };

        consumer.save(listId, request).then((response) => {
            if (response.ok) {
                response.json().then((result) => {
                    dispatch(events.saved(listId, result));
                    setState({ name: "" });
                    formRef.current.reset();
                });
            }

        });

    }

    const onEdit = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id: item.id,
            completed: item.completed
        };

        consumer.update(listId, request).then((response) => {
            if (response.ok) {
                response.json().then((result) => {
                    dispatch(events.updated(listId, result));
                    setState({ name: "" });
                    formRef.current.reset();
                });
            }

        });
    }

    return <div id="nuevoitem">
        <form ref={formRef}>
            
            <input type="text" name="name" placeholder={"Agregar un nuevo item "} defaultValue={item.name} onChange={(event) => {
                setState({ ...state, name: event.target.value })
            }} ></input>
            <br></br>
            <center>
                {item.id && <button className="button button2" onClick={onEdit}>Actualizar</button>}
                {!item.id && <button className="button button1" onClick={onAdd}>Agregar</button>}
            </center>
            <br></br>
        </form>
    </div>
}