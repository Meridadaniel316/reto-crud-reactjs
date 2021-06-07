import React, { useContext, useState, useRef } from 'react';
import consumer from "../tools/consumer";
import events from "../tools/events";
import Store from "../store";

export default () => {
    const { dispatch } = useContext(Store);
    const formRef = useRef(null);
    const [state, setState] = useState({ name: "" });

    const onCreate = (event) => {
        event.preventDefault();
        consumer.saveGroup({ name: state.name, id: null })
            .then((response) => {
                if (response.ok) {
                    response.json().then((newList) => {
                        dispatch(events.savedGroup(newList));
                        formRef.current.reset();
                        setState({ name: "" })
                    })
                }
            });

    };

    return <div id="nuevogroup">
        <form ref={formRef}>
            <center>Agregar un nuevo elemento <b>al CRUD</b> <br></br>
                <b>NOTA:</b> El sistema fue desarrollado por Daniel Castaño Merida del training ciclo 2 Sofka U</center>
            <br></br>

            <input
                type="text"
                name="name"
                placeholder="Lista de TO-DO"
                onChange={(event) => {
                    setState({ name: event.target.value })
                }}  ></input>
            <br></br>
            <center>
                <button className="button button1" onClick={onCreate}>Nueva lista</button>
            </center>
            <br></br>
        </form>
    </div>
};
