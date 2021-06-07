import React, { useContext, useEffect } from 'react';
import ToDoForm from "../forms/FormItems";
import ToDoList from "../lists/ListItems";
import consumer from "../tools/consumer";
import events from "../tools/events";
import Store from "../store"

export default () => {
    const { state: { list, todo }, dispatch } = useContext(Store);
    useEffect(() => {
        consumer.findAllGroup().then((response) => {
            if(response.ok) {
                response.json().then((list) => {
                    dispatch(events.findedGroup(list));
                });
            }
        })
    }, [dispatch]);

    const onDelete = (listId) => {
        consumer.deleteGroup(listId).then((response) => {
            if(response.ok) {
                dispatch(events.deletedGroup(listId));
            }
        })
    };

    return <div>
        {list.elements.length === 0 && <div><center><b>Actualmente no hay grupos creados</b></center></div>}
        {list.elements.map((element) => {
            return <div key={element.id} id={"list-to-do-"+element.id}>
                <div id="listaitems">
                   
                        --|===(<b>{element.name.toUpperCase()}</b>)===|--
                        <button className="button buttondelete grou" onClick={() => onDelete(element.id)}>Eliminar</button>
                        (<b>ID: {element.id}</b>)
                    <ToDoForm listId={element.id} todo={todo} />
                    <ToDoList listId={element.id} todo={todo} />
                </div>
            </div>
        })}
    </div>
}