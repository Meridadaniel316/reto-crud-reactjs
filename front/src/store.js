import React, { useReducer, createContext } from 'react';
import listReducer from "./tools/reducer";

const initialState = {
    list: {
        elements: []
    },
    todo: {
        elements: [],
        item: {}
    }
};
const Store = createContext(initialState);

const merge = { ...listReducer() };
function reducer(state, action) {
    return merge[action.type] ? merge[action.type](state, action) : state;
}

export const StoreProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return <Store.Provider value={{ state, dispatch }}>
        {children}
    </Store.Provider>
};

export default Store;