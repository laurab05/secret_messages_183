import { useState } from 'react'
import { login } from '../api/auth'

export default function LoginForm() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')
    
    async function handleSubmit(e) {
        e.preventDefault()
        try {
            await login(username, password)
            setMessage('Logged in successfully!')
        } catch (error) {
            setMessage('Login failed')
        }
    }

    return (
        <div>
            <h2>Login</h2>
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
            <button onClick={handleSubmit}>Login</button>
            <p>{message}</p>
        </div>
    )
}