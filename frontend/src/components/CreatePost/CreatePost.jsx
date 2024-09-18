import Navbar from '../Navbar/Navbar';
import { TextField, Button, Container, Typography, Grid, Paper } from '@mui/material';
import { useState } from 'react';
import axios from "axios";

const CreatePost = () => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [tags, setTags] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = sessionStorage.getItem("jwt");
            const response = await axios.post("http://localhost:8080/post/create", {
                title: title,
                content: content,
                tags: tags.split(' ')
            }, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            alert(response.data);
            setTags('');
            setTitle('');
            setContent('');
        } catch (err) {
            console.log(err);
        }
    }

    return (
        <>
            <Navbar />
            <Container component="main" maxWidth="xs" sx={{ marginTop: 4 }}>
                <Paper elevation={3} sx={{ padding: 3, display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                    <Typography variant="h5">Create a New Blog Post</Typography>
                    <form noValidate autoComplete="off" onSubmit={handleSubmit}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    label="Title"
                                    variant="outlined"
                                    value={title}
                                    onChange={(e) => setTitle(e.target.value)}
                                    required
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    label="Content"
                                    variant="outlined"
                                    value={content}
                                    onChange={(e) => setContent(e.target.value)}
                                    multiline
                                    rows={6}
                                    required
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    fullWidth
                                    label="Tags (Separated by space)"
                                    variant="outlined"
                                    value={tags}
                                    onChange={(e) => setTags(e.target.value)}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                >
                                    Submit
                                </Button>
                            </Grid>
                        </Grid>
                    </form>
                </Paper>
            </Container>
        </>
    );
};

export default CreatePost;
