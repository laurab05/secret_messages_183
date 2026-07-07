import { useState } from 'react'
import { register } from '../api/auth'

export default function RegisterForm() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')
    
    async function handleSubmit(e) {
        e.preventDefault()
        try {
            await register(username, password)
            setMessage('Registered successfully!')
        } catch (error) {
            setMessage('Registration failed')
        }
    }

    return (
        <div>
            <h2>Register</h2>
            <label>
                Your username:
            </label>
            <input 
                value={username} 
                onChange={e => setUsername(e.target.value)} 
            />
            <label>
                Your password:
            </label>
            <input 
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
            />
            <label>
                Submit
            </label>
            <button onClick={handleSubmit}>Register</button>
            <p>{message}</p>
        </div>
    )
}